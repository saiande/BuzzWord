package controller;

import java.io.IOException;

/**
 * @author Ritwik Banerjee
 */
public interface FileController {

    void handleHomeRequest() throws IOException;

    void handleCreateProfileRequest() throws IOException;

    void handleLoginLogoutRequest() throws IOException;

    void handleProfileRequest() throws IOException;

    void handleSelectModeRequest() throws IOException;

    void handleStartPlayingRequest() throws IOException;

    void handlePlayPauseRequest() throws IOException;

    void handleXRequest() throws IOException;

    void handleLoginRequest() throws IOException;

    void handleOneRequest() throws IOException;

    void handleTwoRequest() throws IOException;

    void handleThreeRequest() throws IOException;

    void handleFourRequest() throws IOException;

    void handleFiveRequest() throws IOException;

    void handleSixRequest() throws IOException;

    void handleSevenRequest() throws IOException;

    void handleEightRequest() throws IOException;

    void handleEnterRequest() throws IOException;

    void handleCancelRequest() throws IOException;

    String getModeTitle();

    int getLevel();

}
