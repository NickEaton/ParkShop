package org.openjfx.util;

import java.io.IOException;

// An interface for files which can be saved in this project
public interface Saveable {

    // Required methods
    void saveToFile() throws IOException;
}
