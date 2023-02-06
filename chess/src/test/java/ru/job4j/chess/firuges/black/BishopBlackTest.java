package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.chess.firuges.Cell.*;

public class BishopBlackTest {

    @Test
    public void whenTheSamePosition() {
        Figure bishopBlack = new BishopBlack(C1);
        Cell expected = bishopBlack.position();
        assertThat(expected, is(C1));
    }

    @Test
    public void whenTheRightCopy() {
        Figure bishopBlack = new BishopBlack(C1);
        Figure expected = bishopBlack.copy(C1);
        assertThat(expected.position(), is(C1));
    }

    @Test
    public void whenPositionFromC1toG5() {
        Figure bishopBlack = new BishopBlack(C1);
        Cell[] expected = bishopBlack.way(G5);
        assertThat(expected, is(new Cell[] {D2, E3, F4, G5}));
    }

    @Test
    public void whenPositionFromG5toC1() {
        Figure bishopBlack = new BishopBlack(G5);
        Cell[] expected = bishopBlack.way(C1);
        assertThat(expected, is(new Cell[] {F4, E3, D2, C1}));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenWrongMoving() {
        Figure bishopBlack = new BishopBlack(C1);
        Cell[] expected = bishopBlack.way(G6);
    }

    @Test (expected = FigureNotFoundException.class)
    public void whenFigureIsAbsent() throws FigureNotFoundException, OccupiedCellException {
        Logic logic = new Logic();
        logic.move(A1, G5);
    }

    @Test
    public void whenTheWayExists() throws FigureNotFoundException, OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(C1);
        logic.add(bishopBlack);
        Cell[] way = bishopBlack.way(G5);
        assertThat(way, is(new Cell[] {D2, E3, F4, G5}));
    }

    @Test (expected = OccupiedCellException.class)
    public void whenArrayWayIsNotFree() throws FigureNotFoundException, OccupiedCellException {
        Logic logic = new Logic();
        logic.add(new RookBlack(E3));
        logic.add(new BishopBlack(C1));
        logic.move(C1, G5);
    }
}