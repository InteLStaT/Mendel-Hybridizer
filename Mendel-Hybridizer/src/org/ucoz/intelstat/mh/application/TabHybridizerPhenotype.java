package org.ucoz.intelstat.mh.application;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.math3.fraction.Fraction;
import org.ucoz.intelstat.mh.genetics.Genotype;
import org.ucoz.intelstat.mh.genetics.Phenotype;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabHybridizerPhenotype {

	public Map<Phenotype, Fraction> ratios;

	public TableView<Phenotype> tblPhenotype;
	public TableColumn<Phenotype, String> colPt;
	public TableColumn<Phenotype, String> colRatios;
	public TableColumn<Phenotype, String> colDesc;

	public void initialize() {
		// ratios is null, implement it in FilialGeneration
		tblPhenotype.setItems(FXCollections.observableList(new ArrayList<>(ratios.keySet())));
		colPt.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().letterRepresentation()));
		colRatios.setCellValueFactory((p) -> new SimpleStringProperty(ratios.get(p.getValue()).toString()));
		colDesc.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().description()));
	}

	public TabHybridizerPhenotype(Map<Phenotype, Fraction> ratios) {
		this.ratios = ratios;
	}
}
