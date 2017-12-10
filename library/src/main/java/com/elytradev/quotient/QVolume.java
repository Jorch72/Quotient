package com.elytradev.quotient;

import java.util.ArrayList;
import java.util.List;

/**
 * An efficient arbitrarily sized volume of blocks, designed to be easily
 * written to and read from Quotient format.
 * @see Quotient
 */
public class QVolume {

	protected final List<QBlockState> dictionary = new ArrayList<>();
	protected int[] blocks;
	
}
