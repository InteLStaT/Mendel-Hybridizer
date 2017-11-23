package org.ucoz.intelstat.mh.application;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.fraction.Fraction;
import org.ucoz.intelstat.mh.genetics.Gamete;
import org.ucoz.intelstat.mh.genetics.Generation;
import org.ucoz.intelstat.mh.genetics.Genetics;
import org.ucoz.intelstat.mh.genetics.Genotype;
import org.ucoz.intelstat.mh.genetics.Organism;
import org.ucoz.intelstat.mh.genetics.ParentalGeneration;
import org.ucoz.intelstat.mh.genetics.Phenotype;
import org.ucoz.intelstat.mh.i18n.I18N;
import org.ucoz.intelstat.mh.res.Resources;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TabHybridizer extends StackPane {

	public Tab tab;
	public TextField fieldExperimentName;
	public TextField fieldParent1;
	public TextField fieldParent2;
	public Button btnHybridize;
	public TitledPane panePossibleGametes;
	public ListView<String> listGametes1;
	public ListView<String> listGametes2;
	public Button btnNextGeneration;
	public VBox vbox;

	private Genotype g1, g2;
	private Generation last;

	public void initialize() {
		fieldExperimentName.textProperty().addListener((a, b, c) -> typedFieldExperimentName());
		fieldParent1.textProperty().addListener((a, b, c) -> typedFieldParent1());
		fieldParent2.textProperty().addListener((a, b, c) -> typedFieldParent2());
	}

	public void typedFieldExperimentName() {
		tab.setText(fieldExperimentName.getText());
	}

	public void typedFieldParent1() {
		resolveParents(fieldParent1.getText(), fieldParent2.getText());
	}

	public void typedFieldParent2() {
		resolveParents(fieldParent1.getText(), fieldParent2.getText());
	}

	public void actionHybridize() {
		fieldParent1.setDisable(true);
		fieldParent2.setDisable(true);
		btnHybridize.setDisable(true);

		Organism o1 = new Organism(g1);
		Organism o2 = new Organism(g2);

		populateGameteLists(o1, o2);
		panePossibleGametes.setVisible(true);
		btnNextGeneration.setVisible(true);

		last = new ParentalGeneration(o1, o2);
		computeNextGeneration();
	}

	public void actionComputeNextGeneration() {
		computeNextGeneration();
	}

	private void resolveParents(String p1, String p2) {
		try {
			g1 = Genetics.parseGenotype(p1);
			g2 = Genetics.parseGenotype(p2);
			if (!g1.phenotype().containsAllelesOfSameGeneAs(g2.phenotype())) {
				throw new IllegalArgumentException("not equivalent genotypes");
			}
			btnHybridize.setDisable(false);
		} catch (IllegalArgumentException | ParseException e) {
			btnHybridize.setDisable(true);
		}
	}

	private void populateGameteLists(Organism o1, Organism o2) {
		Set<Gamete> gametes1 = o1.possibleGametes();
		Set<Gamete> gametes2 = o2.possibleGametes();
		listGametes1.setItems(
				FXCollections.observableArrayList(gametes1.stream().map((g) -> g.toString()).toArray(String[]::new)));
		listGametes2.setItems(
				FXCollections.observableArrayList(gametes2.stream().map((g) -> g.toString()).toArray(String[]::new)));
	}

	private void computeNextGeneration() {
		try {
			last = last.nextGeneration();
			TitledPane gtp = createGenotypePane(last.genotypicRatios());
			TitledPane ptp = createPhenotypePane(last.phenotypicRatios());
			addGenerationNodes(gtp, ptp);
		} catch (IOException e) {
			System.err.println("Error loading fxml:");
			e.printStackTrace();
		}
	}

	private void addGenerationNodes(TitledPane genotypePane, TitledPane phenotypePane) {
		Separator lsep = new Separator(Orientation.HORIZONTAL);
		Separator rsep = new Separator(Orientation.HORIZONTAL);
		Label lblGeneration = new Label(I18N.get("tab.hybridizer.label.generation", last.abbreviation())/*TODO replace with generation descriptor*/);
		lblGeneration.getStyleClass().add("label-generation");
		HBox separator = new HBox();
		separator.getChildren().add(lsep);
		separator.getChildren().add(lblGeneration);
		separator.getChildren().add(rsep);
		separator.setAlignment(Pos.CENTER);
		HBox.setHgrow(lsep, Priority.ALWAYS);
		HBox.setHgrow(rsep, Priority.ALWAYS);

		vbox.getChildren().add(vbox.getChildren().size() - 1, separator);
		vbox.getChildren().add(vbox.getChildren().size() - 1, genotypePane);
		vbox.getChildren().add(vbox.getChildren().size() - 1, phenotypePane);
	}

	private TitledPane createGenotypePane(Map<Genotype, Fraction> ratios) throws IOException {
		FXMLLoader gtloader = new FXMLLoader(Resources.get("TabHybridizerGenotype.fxml"));
		I18N.apply(gtloader);
		TabHybridizerGenotype controller = new TabHybridizerGenotype(ratios);
		gtloader.setController(controller);
		return gtloader.load();
	}

	private TitledPane createPhenotypePane(Map<Phenotype, Fraction> ratios) throws IOException {
		FXMLLoader ptloader = new FXMLLoader(Resources.get("TabHybridizerPhenotype.fxml"));
		I18N.apply(ptloader);
		TabHybridizerPhenotype controller = new TabHybridizerPhenotype(ratios);
		ptloader.setController(controller);
		return ptloader.load();
	}

	public void freeResources() {
		g1 = g2 = null;
		last = null;
		System.out.println("FREE RESOURCES WAS CALLED HURRAY.");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		freeResources();
	}
}
