package dev.mccue.tui;

import dev.mccue.tui.os.windows.x86.*;

import java.lang.foreign.Arena;

final class TerminalOperations_Windows_x86 extends TerminalOperations {
    @Override
    boolean isTerminal(int fd) {
        var handle = windows_h.GetStdHandle(windows_h.STD_OUTPUT_HANDLE());
        try (var arena = Arena.ofConfined()) {
            var st = arena.allocate(windows_h.C_INT);
            return windows_h.GetConsoleMode(
                    handle,
                    st
            ) == 0;
        }
    }

    @Override
    void makeRaw(int fd) {
        throw new UnsupportedOperationException();
    }
}
