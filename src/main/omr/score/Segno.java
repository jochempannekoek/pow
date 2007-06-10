//----------------------------------------------------------------------------//
//                                                                            //
//                                 S e g n o                                  //
//                                                                            //
//  Copyright (C) Herve Bitteur 2000-2007. All rights reserved.               //
//  This software is released under the GNU General Public License.           //
//  Contact author at herve.bitteur@laposte.net to report bugs & suggestions. //
//----------------------------------------------------------------------------//
//
package omr.score;

import omr.glyph.Glyph;

import omr.score.visitor.ScoreVisitor;

/**
 * Class <code>Segno</code> represents a segno event
 *
 * @author Herv&eacute Bitteur
 * @version $Id$
 */
public class Segno
    extends AbstractDirection
{
    //~ Constructors -----------------------------------------------------------

    //-------//
    // Segno //
    //-------//
    /**
     * Creates a new instance of Segno event
     *
     * @param measure measure that contains this mark
     * @param point location of mark
     * @param chord the chord related to the mark, if any
     * @param glyph the underlying glyph
     */
    public Segno (Measure     measure,
                  SystemPoint point,
                  Chord       chord,
                  Glyph       glyph)
    {
        super(measure, point, chord, glyph);
    }

    //~ Methods ----------------------------------------------------------------

    //--------//
    // accept //
    //--------//
    @Override
    public boolean accept (ScoreVisitor visitor)
    {
        return visitor.visit(this);
    }

    //----------//
    // populate //
    //----------//
    /**
     * Used by ScoreBuilder to allocate the segno marks
     *
     * @param glyph underlying glyph
     * @param measure measure where the mark is located
     * @param point location for the mark
     */
    static void populate (Glyph       glyph,
                          Measure     measure,
                          SystemPoint point)
    {
        Slot slot = measure.getClosestSlot(point);
        glyph.setTranslation(
            new Segno(measure, point, slot.getChordBelow(point), glyph));
    }
}