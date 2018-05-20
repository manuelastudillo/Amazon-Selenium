package com.everis.beca.amazon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;






public class Main {

	public static void main(String[] args) throws InterruptedException {
	
		File chromeDriverFile = new File ("C:\\drivers/chromedriver.exe");	
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();

		
		 WebElement inputBuscarAmazony = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));
		 WebElement buttonBuscar  = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));
		 inputBuscarAmazony.sendKeys("teclado");
		 inputBuscarAmazony.sendKeys(Keys.ENTER);
			
				
				Actions action = new Actions(driver);

				
				List<WebElement> productosLista = driver.findElements(By.xpath(("//*[@id=\"atfResults\"]/ul/li//div/a/h2")));	
				int randomProductos = new Random().nextInt(productosLista.size());
				action.moveToElement(productosLista.get(randomProductos +1)).build().perform();
				productosLista.get(randomProductos).click();
				Thread.sleep(1000);
              

				if(driver.findElements(By.xpath("//div[@id='productPrice']/p/span[contains(@itemprop,'highPrice')]")).equals(" ")) {
 
					
				
				String nombre = driver.findElement(By.xpath("//span[@id=\"productTitle\"]")).getText();
				
				String precio = driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
				String link = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img")).getAttribute("src");
				String descripcion = driver.findElement(By.xpath("//div[@id=\"feature-bullets\"]/ul")).getText();
				Producto newproducto = new Producto(nombre, precio, link, descripcion);
				List<Producto> listaProductos = new ArrayList<>();
				listaProductos.add(newproducto);
				
				System.out.println(nombre + "/" + precio + "/" + link + " / " + descripcion);
				driver.navigate().back();

				Thread.sleep(1000);
				
				FileWriter fw;
				
				try {
					
					fw = new FileWriter(new File(newproducto.getNombre() + ".txt"));
					
					fw.write(("Nombre Producto:\n"+newproducto.getNombre()));
					fw.write(("Precio Producto:\n"+newproducto.getPrecio()));
					fw.write(("Link Oferta:\n"+newproducto.getLink()));
					fw.write(("Precio Descripcion:\n"+newproducto.getDescripcion()));
					fw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				FileWriter filewriter = null;
				PrintWriter printw = null;
				try {
					filewriter = new FileWriter(newproducto.getNombre() +".html");
					printw = new PrintWriter(filewriter);
					printw.println("<html>");
					printw.println("<body>");
					printw.println("<p>Nombre producto:" + newproducto.getNombre() + "<p/>");
					printw.println("<p>Precio producto:" + newproducto.getPrecio() + "</p>");
					printw.println("<p>Imagen producto: <img src=" + newproducto.getLink() + "></p>");
					printw.println("<p> Descripción:" + newproducto.getDescripcion() + "></p>");
					printw.println("<br>------------------------------<br>");
					printw.println("</body>");
					printw.println("</html>");
					printw.close();
					System.out.println("Generado exitosamente");
				} catch (Exception e) {
					e.getMessage();
				}


			
				
               }
				else {
					
					String nombre = driver.findElement(By.xpath("//span[@id=\"productTitle\"]")).getText();
					String precio = driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();

					String link = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img")).getAttribute("src");
					String descripcion = driver.findElement(By.xpath("//div[@id=\"feature-bullets\"]/ul")).getText();
					Producto newproducto = new Producto(nombre, precio, link, descripcion);
					List<Producto> listaProductos = new ArrayList<>();
					listaProductos.add(newproducto);
					
					System.out.println(nombre + "/" + precio + "/" + link + " / " + descripcion);
					driver.navigate().back();

					Thread.sleep(1000);
					
					FileWriter fw;
					
					try {
						
						fw = new FileWriter(new File(newproducto.getNombre() + ".txt"));
						
						fw.write(("Nombre Producto:\n"+newproducto.getNombre()));

						fw.write(("Link Oferta:\n"+newproducto.getLink()));
						fw.write(("Producto Descripcion:\n"+newproducto.getDescripcion()));
						fw.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
					
					FileWriter filewriter = null;
					PrintWriter printw = null;
					try {
						filewriter = new FileWriter(newproducto.getNombre() +".html");
						printw = new PrintWriter(filewriter);
						printw.println("<html>");
						printw.println("<body>");
						printw.println("<p>Nombre producto:" + newproducto.getNombre() + "<p/>");
						printw.println("<p>Precio producto:" + newproducto.getPrecio() + "</p>");
						printw.println("<p>Imagen producto: <img src=" + newproducto.getLink() + "></p>");
						printw.println("<p> Descripción:" + newproducto.getDescripcion() + "></p>");
						printw.println("<br>------------------------------<br>");
						printw.println("</body>");
						printw.println("</html>");
						printw.close();
						System.out.println("Generado exitosamente");
					} catch (Exception e) {
						e.getMessage();
					}
					}
				
				
			
					
			}


			

	
	private static WebElement isElementPresent(WebDriver driver, String xpath, int timeOut) {
		WebElement result = null;

		int countTimeOut = 0;
		do {
			try {
				Thread.sleep(1000);
				result = driver.findElement(By.xpath(xpath));

			} catch (Exception e) {
				System.err.println("Espero 1 segundo.");
			}
			countTimeOut++;
		} while (result == null && countTimeOut < timeOut);

		return result;

	}
}


