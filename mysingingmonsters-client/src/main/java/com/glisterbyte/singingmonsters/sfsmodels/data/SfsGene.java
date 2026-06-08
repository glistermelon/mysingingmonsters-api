package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsGene extends SfsModel {

    // Skipped keys
    // gene_graphic
    // last_changed
    // min_server_version
    // sort_order

    public int gene_id;

    /**
     * A distinct character (length-1 string) identifying the element
     */
    public String gene_letter;

    /**
     * Key for the local text resource corresponding to the element's name
     */
    public String gene_string;

}