package views;

import helpers.instagram.Instagram;
import helpers.qiwi.Qiwi;
import helpers.vk.VkChecker;
import models.Mappers;

import java.util.ArrayList;

public class Views
{
    private ArrayList<View> list;
    private MainMenu mainMenu;
    private int mainMenuId;
    private MyProfile myProfile;
    private int myProfileId;
    private AddVkAccount addVkAccount;
    private int addVkAccountId;
    private AddQiwiProfile addQiwiProfile;
    private int addQiwiProfileId;
    private AddInstagramId addInstagramId;
    private int addInstagramIdId;
    private GetJob getJob;
    private int getJobId;
    public Views(Mappers newMappers, VkChecker vkChecker, Qiwi newQiwi, Instagram newInstagram)
    {
        mainMenu = new MainMenu(newMappers, this, newQiwi);
        mainMenuId = 0;
        myProfile = new MyProfile(newMappers, this);
        myProfileId = 1;
        addVkAccount = new AddVkAccount(newMappers, this, vkChecker);
        addVkAccountId = 2;
        addQiwiProfile = new AddQiwiProfile(newMappers, this);
        addQiwiProfileId = 3;
        addInstagramId = new AddInstagramId(newMappers, this, newInstagram);
        addInstagramIdId = 4;
        getJob = new GetJob(newMappers, this, vkChecker);
        getJobId = 5;
        list = new ArrayList<>();
        list.add(mainMenu);
        list.add(myProfile);
        list.add(addVkAccount);
        list.add(addQiwiProfile);
        list.add(addInstagramId);
        list.add(getJob);
    }

    public int getGetJobId()
    {
        return getJobId;
    }

    public GetJob getGetJob()
    {
        return getJob;
    }

    public AddInstagramId getAddInstagramId()
    {
        return addInstagramId;
    }

    public int getAddInstagramIdId()
    {
        return addInstagramIdId;
    }

    public AddQiwiProfile getAddQiwiProfile()
    {
        return addQiwiProfile;
    }

    public int getAddQiwiProfileId()
    {
        return addQiwiProfileId;
    }

    public int getMainMenuId()
    {
        return mainMenuId;
    }

    public int getMyProfileId()
    {
        return myProfileId;
    }

    public int getAddVkAccountId()
    {
        return addVkAccountId;
    }

    public MainMenu getMainMenu()
    {
        return mainMenu;
    }

    public AddVkAccount getAddVkAccount()
    {
        return addVkAccount;
    }

    public MyProfile getMyProfile()
    {
        return myProfile;
    }

    public View getById(int viewId)
    {
        return list.get(viewId);
    }
}
