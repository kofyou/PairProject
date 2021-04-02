import dao.UserDaoimpl;
import net.sf.json.JSONObject;
import pojo.User;

public class test
{
    public static void main(String args[])
    {
        String account="123";
        String password="123";
        JSONObject jsonObject=new JSONObject();
        UserDaoimpl userDaoimpl=new UserDaoimpl();
        if(!userDaoimpl.IsUserExist(account))
        {
            jsonObject.put("type","0");
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            System.out.println(jsonObject);
        }

        User user=userDaoimpl.GetUser(account);
        if(user.getPassword().equals(password))
        {
            jsonObject.put("type","1");
            jsonObject.put("name",user.getUsername());
            jsonObject.put("address",user.getAddress());
            jsonObject.put("company",user.getCompany());
            jsonObject.put("info",user.getInfo());
            System.out.println(jsonObject);
        }
        else
        {
            jsonObject.put("type","0");
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            System.out.println(jsonObject);
        }
    }
}
