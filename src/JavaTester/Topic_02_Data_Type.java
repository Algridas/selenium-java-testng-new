package JavaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {
	
	public static void main(String[] args) {
		//Kiểu dữ liệu nguyên thủy (Primitive data type)
		//Số nguyên: byte short int long (không có phần thập phân)
		//kích thước, độ rộng để lưu trữ dữ liệu từ nhỏ đến lớn
		
		//Số thực: float double (có phần thập phân)
		float studentPoint = 9.5f;
		double employeeSalary = 35.6d;
		
		
		//Logic: boolean
		
		//Kí tự: char
		
	
		//Kiểu dữ liệu tham chiếu (Reference data type)
		// Class
		FirefoxDriver driver = new FirefoxDriver();
		
		
		// Interface
		WebElement firstNameTextBox;
		
		
		//String
		String firstName = "Automation Testing";
		
		//Object
		Object people;
		
		//Array
		String[] studentName = {"Nguyễn Văn An", "Adulahkhor", "Halil"};
		
		
		//Collection: List/ Set /Queue
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		
		//Map
		Map<String, Integer> student = new HashMap<String, Integer>();
		
		
		
		
		
		
		
	}

}
