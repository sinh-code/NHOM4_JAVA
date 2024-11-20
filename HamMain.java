package Baimau4;

import java.util.Scanner;

public class HamMain {

	
	public static void main(String[] args) {
		ProjectorManager ql=new ProjectorManager();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("-------MENU---------------------");
			System.out.println("1.NHAP  ");
			System.out.println("2.HIEN THI  ");
			System.out.println("3.TIM KIEM ");
			System.out.println("4.THEM SAN PHAM ");
			System.out.println("5.SUA ");
			System.out.println("6.XOA SAN PHAM");
			System.out.println("7.THOAT ");
			int i=0;
			int chon=sc.nextInt();
			sc.nextLine();
			switch(chon) {
				case 1:
					ql.NHAP();
					break;
				case 2:
					ql.HienThi();
					break;
				case 3:
					System.out.println("Nhap ma dinh danh can tim kiem:");
					String product_id=sc.nextLine();
					ql.searchProjector(product_id);
					break;
				case 4:
					//ql.addProjector(null);
					ql.NHAP();
					break;
				case 5:
					System.out.print("Nhap ma dinh danh can sua:");
					String Ma=sc.nextLine();
					ql.editProjector(Ma);
					break;
				case 6:
					System.out.println("Nhap ma can xoa:");
					String product=sc.nextLine();
					ql.delProjector(product);
					break;
				case 7:
					System.exit(0);
					break;
				default:
					System.out.println("Ban nhap sai lua chon ,yeu cau nhap lai!");
					break;
			}
		}
	}

}
