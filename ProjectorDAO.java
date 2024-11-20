package Baimau4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ProjectorDAO implements Serializable {
	private static final String FILE_NAME="Projector.DAT";
	private ArrayList<Projector> list=new ArrayList<Projector>();
	
//	public void GHI(ArrayList<Projector> list) {
//		try {
//			ObjectOutputStream oss=new ObjectOutputStream(new FileOutputStream(FILE_NAME));
//			oss.writeObject(oss);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//				
//	}
//	public ArrayList<Projector> DOC(){
//		try {
//			ObjectInputStream fis=new ObjectInputStream(new FileInputStream(FILE_NAME));
//				list=(ArrayList<Projector>)fis.readObject();
//				return list;
//		} catch (FileNotFoundException e) {
//			return new ArrayList<Projector>();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	public void GHI(ArrayList<Projector> list) {
		try {
			ObjectOutputStream oss=new ObjectOutputStream (new FileOutputStream(FILE_NAME));
			oss.writeObject(list);
			oss.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Projector> DOC(){
		
		try {
			ObjectInputStream fis=new ObjectInputStream (new FileInputStream(FILE_NAME));
				list=(ArrayList<Projector>) fis.readObject();
				fis.close();
		} catch (FileNotFoundException e) {
			return new ArrayList<Projector>();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	public static void main(String[] args) {
		

	}

}
