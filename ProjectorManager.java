package Baimau4;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectorManager {
	ProjectorDAO st = new ProjectorDAO();
	ArrayList<Projector> list = new ArrayList<>();

	public ProjectorManager() {
		list = st.DOC();
	}

	public void NHAP() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma dinh danh:");
		String product_id = sc.nextLine();
		System.out.print("Nhap ten:");
		String product_name = sc.nextLine();
		System.out.print("Nhap gia:");
		Double product_price = sc.nextDouble();
		System.out.print("Nhap so luong:");
		int product_total = sc.nextInt();
		sc.nextLine();
		System.out.print("Nhap hang san xuat :");
		String projector_brand = sc.nextLine();
		System.out.print("Nhap nam san xuat:");
		String projector_year = sc.nextLine();

		Projector p = new Projector(product_id, product_name, product_price, product_total, projector_brand,
				projector_year);
		list.add(p);
	}

	public void HienThi() {
		for (Projector pr : list) {
			System.out.println(pr);
		}
	}

	public void addProjector(Projector st) {
		list.add(st);
	}

	public void editProjector(String product_id) {
		Scanner sc = new Scanner(System.in);
		if (list.isEmpty()) {
			System.out.println("Hien danh sach dang rong!");
		}
		boolean found = false;
		for (Projector mh : list) {
			if (mh.getProduct_id().equalsIgnoreCase(product_id)) {
				found = true;
			}
			System.out.println("Nhap ma SP moi:");
			String product_idM = sc.nextLine();
			if (!product_idM.isEmpty()) {
				mh.setProduct_id(product_idM);
			}
			System.out.println("Nhap ten SP moi:");
			String product_nameM = sc.nextLine();
			if (!product_nameM.isEmpty()) {
				mh.setProduct_name(product_nameM);
			}
			System.out.println("Nhap gia SP moi:");
			String product_priceM = sc.nextLine();
			if (!product_priceM.isEmpty()) {
				double priceM = Double.parseDouble(product_priceM);
				mh.setProduct_price(priceM);
			}
			System.out.println("Nhap so luong SP moi:");
			String product_totalM=sc.nextLine();
			if(!product_totalM.isEmpty()) {
				int TotalM= Integer.parseInt(product_totalM);
				mh.setProduct_total(TotalM);
			}
			System.out.print("Nhap hang san xuat moi:");
			String project_branM=sc.nextLine();
			if(!project_branM.isEmpty()) {
				mh.setProjector_brand(project_branM);
			}
			System.out.print("Nhap nam san xuat moi:");
			String project_yearM=sc.nextLine();
			if(!project_yearM.isEmpty()) {
				mh.setProjector_year(project_yearM);
			}
			break;
		}
		if(!found) {
			System.out.println("Khong tim thay ma can sua!");
		}
	}

	public void delProjector(String product_id) {
//		for(Projector st:list) {
//			list.removeIf(c->c.getProduct_id().equalsIgnoreCase(product_id));
//		}
		boolean removed = list.removeIf(c -> c.getProduct_id().equalsIgnoreCase(product_id));
		if (removed) {
			System.out.println("Đã xóa sản phẩm có mã: " + product_id);
		} else {
			System.out.println("Không tìm thấy sản phẩm có mã: " + product_id);
		}
	}

	public void searchProjector(String product_id) {
		boolean found = false;
		if (list.isEmpty()) {
			System.out.println("Danh sach dang rong!");
		}
		for (Projector st : list) {
			if (st.getProduct_id().equalsIgnoreCase(product_id)) {
				System.out.println("Tìm thấy sản phẩm:");
				System.out.println(st);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Không tìm thấy sản phẩm có mã: " + product_id);
		}
	}

}
