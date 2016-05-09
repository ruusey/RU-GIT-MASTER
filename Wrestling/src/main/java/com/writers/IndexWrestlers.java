package com.writers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.models.Wrestler;
import com.owlike.genson.Genson;

public class IndexWrestlers extends Index{




	@Override
	public void buildWrestler(List<Wrestler> objectList) {
		for (Wrestler w : objectList) {
			objectMap.put(w.getId(),w);
			index(w.getId(),w.getName()+ " "+w.getWeight() + w.getEmail());
		}		
		
	}
	
	public static void main(String[] args) throws IOException {
		Genson gen = new Genson();
		ManagerIO io = new ManagerIO();
		List<Wrestler> list = null;
		
		try {
			list = io.getAllWrestlers();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexWrestlers iw = new IndexWrestlers();
		iw.buildWrestler(list);
		System.out.println(gen.serialize(list));
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Enter String");
	        String s = br.readLine();
	        list = (List<Wrestler>) (Object) iw.search(s);
	        
	        for( Wrestler w:list){
	        	System.out.println(w.getName());
	        }
	        try{
	            
	        }catch(NumberFormatException nfe){
	          
	        }
	        
			
		}
		
	}

	
	
	


}
