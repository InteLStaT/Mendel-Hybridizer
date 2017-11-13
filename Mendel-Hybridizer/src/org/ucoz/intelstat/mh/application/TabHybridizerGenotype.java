package org.ucoz.intelstat.mh.application;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;
import org.ucoz.intelstat.mh.genetics.Genotype;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabHybridizerGenotype {

	public Map<Genotype, Fraction> ratios;
	
	public TableView<Genotype> tblGenotype;
	public TableColumn<Genotype, String> colGt;
	public TableColumn<Genotype, String> colRatios;
	
	public void initialize() {
		tblGenotype.setItems(FXCollections.observableList(new ArrayList<>(ratios.keySet())));
		colGt.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().letterRepresentation()));
		colRatios.setCellValueFactory((p) -> new SimpleStringProperty(ratios.get(p.getValue()).toString()));
	}
	
	public TabHybridizerGenotype(Map<Genotype, Fraction> ratios) {
		this.ratios = ratios;
	}
	
}
