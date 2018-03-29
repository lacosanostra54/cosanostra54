from InstagramAPI import InstagramAPI
from http.server import BaseHTTPRequestHandler, HTTPServer
import json


def get_by_id(user_id):
    global API
    following = []
    next_max_id = True
    while next_max_id:
        if next_max_id is True:
            next_max_id = ''
        _ = API.getUserFollowings(user_id, maxid=next_max_id)
        following.extend(API.LastJson.get('users', []))
        next_max_id = API.LastJson.get('next_max_id', '')
    unique_following = {
        f['pk']: f
        for f in following
    }
    return unique_following


def get_id(username):
    API.searchUsername(username)
    json = API.LastJson
    if json['status'] == 'ok':
        return json['user']['pk']
    else:
        return None


class Server(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.headers["Type"] == "Check":
            id = self.headers["FollowerId"]
            id2 = self.headers["FollowedId"]
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            ans = get_by_id(id)
            message = "NO"
            if int(id2) in ans:
                message = "YES"
            self.wfile.write(bytes(message, "utf8"))
        else:
            name = self.headers["Name"]
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            ans = get_id(name)
            if ans:
                message = "Y" + str(ans)
            else:
                message = "N"
            self.wfile.write(bytes(message, "utf8"))


with open('params.json', 'r') as myFile:
    data = myFile.read()
params = json.loads(data)
server_address = ('127.0.0.1', 8081)
API = InstagramAPI(params['InstagramLogin'], params['InstagramPassword'])
API.login()
httpd = HTTPServer(server_address, Server)
httpd.serve_forever()
