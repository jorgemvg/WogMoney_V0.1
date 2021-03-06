package co.wog.monedero.utils;

import java.util.ArrayList;
import java.util.List;

import co.wog.monedero.model.ComboItem;

public class CombosData {

	public List<ComboItem> getTiposdeIdentificacion () {
		
		List lista = new ArrayList<ComboItem>();

		lista.add( new ComboItem("1", "Cédula de Ciudadanía") );
		lista.add( new ComboItem("2", "Cédula de Extranjeria") );
		lista.add( new ComboItem("3", "NIT") );
		
		return lista;
	}
	
	public List<ComboItem> getBancos () {
		
		List lista = new ArrayList<ComboItem>();

		lista.add( new ComboItem("1", "BANCOLOMBIA") );
		lista.add( new ComboItem("2", "BBVA") );
		lista.add( new ComboItem("3", "COLPATRIA") );
		lista.add( new ComboItem("4", "DAVIVIENDA") );
		
		return lista;
	}
	
	public List<ComboItem> getTiposDeCuenta () {
		
		List lista = new ArrayList<ComboItem>();

		lista.add( new ComboItem("1", "Cuenta de Ahorros") );
		lista.add( new ComboItem("2", "Cuenta Corriente") );
		
		return lista;
	}
	
	public List<ComboItem> getCiudades () {
		
		List lista = new ArrayList<ComboItem>();

		lista.add( new ComboItem("1", "Medellin") );
		lista.add( new ComboItem("2", "Bogotá") );
		lista.add( new ComboItem("3", "Cali") );
		lista.add( new ComboItem("4", "Barranquilla") );
		lista.add( new ComboItem("5", "Manizales") );
		lista.add( new ComboItem("6", "Cartagena") );
		lista.add( new ComboItem("7", "Bucaramanga") );
		lista.add( new ComboItem("8", "Pereira") );
		lista.add( new ComboItem("9", "Ibagué") );
		lista.add( new ComboItem("10", "Cucuta") );
		lista.add( new ComboItem("11", "Santa Marta") );
		lista.add( new ComboItem("12", "Pasto") );
		lista.add( new ComboItem("13", "Montería") );
		lista.add( new ComboItem("14", "Armenia") );
		lista.add( new ComboItem("15", "Valledupar") );
		lista.add( new ComboItem("16", "Villavicencio") );
		lista.add( new ComboItem("17", "Popayán") );
		lista.add( new ComboItem("18", "Neiva") );
		lista.add( new ComboItem("19", "Tunja") );
		lista.add( new ComboItem("20", "Sincelejo") );
		
		return lista;
	}
	
}
