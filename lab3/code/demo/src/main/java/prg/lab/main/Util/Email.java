package prg.lab.main.Util;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class Email {
    
    
    private String to;
    
    private String subject;
    
    private String body;

}
