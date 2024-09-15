package com.project.demo.Security;

import com.project.demo.entity.Usuario;
import com.project.demo.entity.UsuarioRole;
import com.project.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado= bCryptPasswordEncoder.encode(passSinCifrar);
        System.out.println("pass cifrado: "+passCifrado);
        Usuario usuario= new Usuario("Waldir","waldirlimaco","waldir.limaco@digitalhouse.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        Usuario usuario2 = new Usuario("gloria", "gloria" ,"gloria@digitalhouse.com",passCifrado, UsuarioRole.ROLE_USER );
        usuarioRepository.save(usuario2);
        Usuario usuario3 = new Usuario("admin", "admin", "admin", passCifrado, UsuarioRole.ROLE_ADMIN );
        usuarioRepository.save(usuario3);

    }
}
