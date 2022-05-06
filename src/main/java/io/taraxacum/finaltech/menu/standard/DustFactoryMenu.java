package io.taraxacum.finaltech.menu.standard;

import io.taraxacum.finaltech.items.machine.AbstractMachine;

import javax.annotation.Nonnull;

/**
 * @author Final_ROOT
 */
public class DustFactoryMenu extends AbstractStandardMachineMenu {
    private static final int[] BORDER = new int[] {3, 4, 5, 12,     14, 21, 22, 23, 30, 31, 32};
    private static final int[] INPUT_BORDER = new int[] {0, 1, 2, 11, 20, 27, 28, 29};
    private static final int[] OUTPUT_BORDER = new int[] {6, 7, 8, 15, 24, 33, 34, 35};
    private static final int[] INPUT_SLOT = new int[] {9, 10, 18, 19};
    private static final int[] OUTPUT_SLOT = new int[] {16, 17, 25, 26};

    public DustFactoryMenu(@Nonnull AbstractMachine abstractMachine) {
        super(abstractMachine);
    }

    @Override
    protected int[] getBorder() {
        return BORDER;
    }

    @Override
    protected int[] getInputBorder() {
        return INPUT_BORDER;
    }

    @Override
    protected int[] getOutputBorder() {
        return OUTPUT_BORDER;
    }

    @Override
    public int[] getInputSlot() {
        return INPUT_SLOT;
    }

    @Override
    public int[] getOutputSlot() {
        return OUTPUT_SLOT;
    }
}
