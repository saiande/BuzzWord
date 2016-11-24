package controller;

import java.io.IOException;

/**
 * @author Sai Ande
 */
public interface FileController {

    void handleHomeRequest() throws IOException;

    void handleCreateProfileRequest() throws IOException;

    void handleProfileRequest() throws IOException;

    void handleSelectModeRequest() throws IOException;

    void handleStartPlayingRequest() throws IOException;

    void handlePauseRequest() throws IOException;

    void handleXRequest() throws IOException;

    void handleLoginRequest() throws IOException;

    void handleOneRequest() throws IOException;

    void handleTwoRequest() throws IOException;

    void handleThreeRequest() throws IOException;

    void handleFourRequest() throws IOException;

    void handleEnterRequest() throws IOException;

    void handleCancelRequest() throws IOException;

    void handleHelpRequest() throws IOException;

    void handleCreateRequest() throws IOException;

    void handleResumeRequest() throws IOException;

    String getUsername() throws IOException;

    String getModeTitle();

    int getLevel();

}
