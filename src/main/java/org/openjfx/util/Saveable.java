package org.openjfx.util;

import java.io.IOException;

// An interface for files which can be saved in this project
public interface Saveable {
    void saveToFile() throws IOException;
}
