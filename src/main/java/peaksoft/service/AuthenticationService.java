package peaksoft.service;

import peaksoft.dto.request.AuthenticationRequest;
import peaksoft.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
