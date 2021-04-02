package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDao;
import entity.collection;
import entity.items;

public class CollectionServlet extends HttpServlet {

	private String action ; //表示收藏夹的动作 ,add,show,delete
	//论文业务逻辑类的对象
	private ItemsDao idao = new ItemsDao();
	
	
	/**
	 * Constructor of the object.
	 */
	public CollectionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("action")!=null)
		{
			this.action = request.getParameter("action");
			if(action.equals("add")) //如果是添加论文进收藏夹
			{
				if(addToCollection(request,response))
				{
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			if(action.equals("show"))//如果是显示收藏夹
			{
			    
				request.getRequestDispatcher("/collection.jsp").forward(request, response);
			}
			if(action.equals("delete")) //如果是执行删除收藏夹的论文
			{
				if(deleteFromCollection(request,response))
				{
					request.getRequestDispatcher("/collection.jsp").forward(request, response);
					
				}
				else
				{
					request.getRequestDispatcher("/collection.jsp").forward(request, response);
				}
			}
		}
		
	}

	//添加论文进收藏夹的方法
	private boolean addToCollection(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		items item = idao.getItemsById(Integer.parseInt(id));
		
		//是否是第一次给收藏夹添加论文,需要给session中创建一个新的收藏夹对象
		if(request.getSession().getAttribute("collection")==null)
		{
			collection collection = new collection();
			request.getSession().setAttribute("collection",collection);
		}
		collection collection = (collection)request.getSession().getAttribute("collection");
		if(collection.addPaperInCollection(item, Integer.parseInt(number)))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	//从收藏夹中删除论文
	private boolean deleteFromCollection(HttpServletRequest request, HttpServletResponse response)
	{
	    
		String id = request.getParameter("id");
		collection coll = (collection)request.getSession().getAttribute("collection");
	    items item = idao.getItemsById(Integer.parseInt(id));
	    if(coll.removePaperFromCollection(item))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	    
}