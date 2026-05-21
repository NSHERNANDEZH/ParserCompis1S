package ide.controller;

import ide.model.CompileRequest;
import ide.model.CompileResponse;
import ide.model.RunRequest;
import ide.model.RunResponse;
import ide.service.CompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CompileController {

    @Autowired
    private CompilerService compilerService;

    @PostMapping("/compile")
    public ResponseEntity<CompileResponse> compile(@RequestBody CompileRequest request) {
        CompileResponse response = compilerService.compile(request.getCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/run")
    public ResponseEntity<RunResponse> run(@RequestBody RunRequest request) {
        RunResponse response = compilerService.run(request.getCode(), request.getStdin());
        return ResponseEntity.ok(response);
    }
}
