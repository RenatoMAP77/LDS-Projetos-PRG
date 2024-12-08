package prg.lab.main.Util.DTOs;

import prg.lab.main.Util.Enums.TipoUsuario;

public class LoginResponseDTO {
    private Long id;
    private TipoUsuario tipoUsuario;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long id, TipoUsuario tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
