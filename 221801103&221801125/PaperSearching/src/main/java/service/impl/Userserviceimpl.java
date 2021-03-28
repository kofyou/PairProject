package service.impl;

import dao.UserDaoimpl;
import pojo.User;

public class Userserviceimpl
{
    private UserDaoimpl userDaoimpl=new UserDaoimpl();

    public int Register(String[] accountinfo)
    {
        if(userDaoimpl.IsUserExist(accountinfo[0]))
        {
            return 0;
        }
        else
        {
            userDaoimpl.InsertUser(accountinfo[0],accountinfo[1]);
            return 1;
        }
    }


    public User Login(String[] accountinfo)
    {
        if(!userDaoimpl.IsUserExist(accountinfo[0]))
        {
            return null;
        }
        User user= userDaoimpl.GetUser(accountinfo[0]);
        if(user.getAccount().equals(accountinfo[0])&&user.getPassword().equals(accountinfo[1]))
        {
            return user;
        }
        return null;
    }


    public void UpdateInfo(User infos)
    {
        userDaoimpl.UpdateUserInfo(infos);
    }

    /*public static void main(String args[])
    {
        String[] str={"123","123"};
        Userserviceimpl userserviceimpl=new Userserviceimpl();
        System.out.println(userserviceimpl.Login(str));
        //String[] str2={"123","25","","dss","6"};
        //userserviceimpl.UpdateInfo(str2);

        //System.out.println(userserviceimpl.userDaoimpl.GetUser("123").toString());

    }*/
}
