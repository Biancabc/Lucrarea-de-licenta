/**
 * This file is part of samples, https://github.com/chocoteam/samples
 *
 * Copyright (c) 2017, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 * See LICENSE file in the project root for full license information.
 */
package problem;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMiddle;
import org.chocosolver.solver.search.strategy.selectors.variables.FirstFail;
import org.chocosolver.solver.search.strategy.selectors.variables.Smallest;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelectorWithTies;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;
import org.chocosolver.util.tools.ArrayUtils;

import java.util.Arrays;

import static org.chocosolver.util.tools.ArrayUtils.append;



/**
 * CSPLib prob034:<br/>
 * "In the Warehouse Location problem (WLP), a company considers opening warehouses
 * at some candidate locations in order to supply its existing stores. Each possible warehouse
 * has the same maintenance C, and a K designating the maximum number of stores
 * that it can supply. Each store must be supplied by exactly one open warehouse.
 * The supply C to a store depends on the warehouse. The objective is to determine which
 * warehouses to open, and which of these warehouses should supply the various stores, such
 * that the sum of the maintenance and supply costs is minimized."
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 04/08/11
 */
public class Warehouse extends AbstractProblem {
    public int W, S, C;
    public int[] K;
    public int[][] P;
    public IntVar[] supplier;
    public BoolVar[] open;
    public IntVar[] cost;
    public IntVar tot_cost;
    public int[] Supplier = new int[100];
    private int[] data;
    public String solutionText;

    public Warehouse(String filePath) {
        this.data = FileParser.numbersExtractedFromFile(filePath);
    }

    public void setUp() {
        int k = 0;
        W = data[k++];
        S = data[k++];
        C = data[k++];
        K = new int[W];

        for (int i = 0; i < W; i++) {
            K[i] = data[k++];
        }
        P = new int[S][W];

        for (int j = 0; j < S; j++) {
            for (int i = 0; i < W; i++) {
                P[j][i] = data[k++];
            }
        }
    }

    @Override
    public void buildModel() {
        //Instanțierea unui nou model
        model = new Model();
        setUp();
        //Variabilele
        //fiecare depozit poate aproviziona unul sau mai multe magazine
        supplier = model.intVarArray("sup", S, 1, W, false);
        //depozitele deschise au valoarea open 1
        open = model.boolVarArray("o", W);
        //costul aprovizionării fiecărui magazin
        cost = model.intVarArray("cPs", S, 1, 96, true);
        //costul total
        tot_cost = model.intVar("C", 0, 99999, true);

        //Constrângeri
        for (int s = 0; s < S; s++) {
            // Un depozit este deschis dacă aprovizionează un magazin
            model.element(model.intVar(1), open, supplier[s], 1).post();
            // Calcularea costului pentru fiecare depozit
            model.element(cost[s], P[s], supplier[s], 1).post();
        }
        for (int w = 0; w < W; w++) {
            IntVar occ = model.intVar("occur_" + w, 0, K[w], true);
            model.count(w + 1, supplier, occ).post();
            occ.ge(open[w]).post();
            occ.le(open[w].mul(100)).post();
        }

        int[] coeffs = new int[W + S];
        Arrays.fill(coeffs, 0, W, C);
        Arrays.fill(coeffs, W, W + S, 1);
        model.scalar(ArrayUtils.append(open, cost), coeffs, "=", tot_cost).post();
    }

    @Override
    public void configureSearch() {
        Solver solver = model.getSolver();
        solver.setSearch(Search.intVarSearch(
                new VariableSelectorWithTies<>(
                        new FirstFail(model),
                        new Smallest()),
                new IntDomainMiddle(false),
                append(supplier, cost, open)));
    }


    @Override
    public void solve() {
        Solver solver = model.getSolver();
        solver.plugMonitor((IMonitorSolution) () -> prettyPrint());
        solver.showShortStatistics();
        solver.getObjectiveManager().<Integer>setCutComputer(obj -> obj);
        solver.findOptimalSolution(tot_cost, false);
    }

    private void prettyOut() {
        System.out.println("Warehouse location problem");
        if (model.getSolver().isFeasible() == ESat.TRUE) {
            prettyPrint();
        }
    }

    private void prettyPrint() {
        StringBuilder st = new StringBuilder();
        st.append("Solutia cu numarul ").append(model.getSolver().getSolutionCount()).append("\n");
        for (int i = 0; i < W; i++) {
            if (open[i].getValue() > 0) {
                st.append(String.format("\tDepozitul %d aprovizioneaza magazinul : ", (i + 1)));
                for (int j = 0; j < S; j++) {
                    if (supplier[j].getValue() == (i + 1)) {
                        Supplier[j] = (i + 1);
                        st.append(String.format("%d ", (j + 1)));
                    }
                }
                st.append("\n");
            }
        }
        st.append("\tCostul total: ").append(tot_cost.getValue());
        solutionText = " ";
        solutionText = st.toString();
        System.out.println(st.toString());
    }

}