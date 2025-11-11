package Planify.Project.Host;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/host")
@RequiredArgsConstructor
public class HostController {

    @GetMapping 
    public ResponseEntity<String> welcomeHost()
    {
        return ResponseEntity.ok("¡Token Correcto! Bienvenido, eres un Host autenticado.");
    }
}
