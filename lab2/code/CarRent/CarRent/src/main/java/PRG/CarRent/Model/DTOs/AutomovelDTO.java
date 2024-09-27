package PRG.CarRent.Model.DTOs;

import PRG.CarRent.Model.ClienteModel;
import PRG.CarRent.Model.EmpresaModel;
import PRG.CarRent.Util.Enums.TipoProprietario;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record AutomovelDTO(       String marca,

 String modelo,

 int ano,

 TipoProprietario tipoProprietario,

 Long cliente,

 Long empresa) {
    
    

}
