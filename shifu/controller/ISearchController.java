/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.controller;

import shifu.core.parameters.SearchParameters;

/**
 *
 * @author delwiv
 */
interface ISearchController {
    public abstract void doSearch(SearchParameters params);
}
