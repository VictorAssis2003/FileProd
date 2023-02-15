package FileModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import FileObject.Objects;

public class FileProd {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Objects> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String filePath = sc.nextLine();
		File path = new File(filePath);
		String finalPath = path + "\\out\\sumary.csv";

		boolean success = new File(filePath + "\\subdir").mkdir();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			while (line != null) {
				String[] items = line.split(",");
				String name = items[0];
				double price = Double.parseDouble(items[1]);
				int quantity = Integer.parseInt(items[2]);

				list.add(new Objects(name, price, quantity));

				line = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(finalPath))) {
				for (Objects item : list) {
					bw.write(item.getName() + ", " + String.format("%.2f", item.total()));
					bw.newLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
		
	}

}
