package comp3350.srsys.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.objects.ChatMessages;
import comp3350.srsys.objects.Product;
import comp3350.srsys.objects.User;

public interface DataAccess
{
	void open(String string) throws Exception;

	void close();

	String getChatMessagesSequential(List<ChatMessages> ChatMessagesResult);

	ArrayList<Product> getAllProducts();

	String getProductSequential(List<Product> productResult);

	String insertProduct(Product currentProduct);

	String updateProduct(Product currentProduct);

	String getUserSequential(List<User> userResult);
}
