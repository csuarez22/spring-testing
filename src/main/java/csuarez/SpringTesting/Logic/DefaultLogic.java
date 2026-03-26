package csuarez.SpringTesting.Logic;

import org.springframework.stereotype.Service;

@Service
public class DefaultLogic implements DefaultLogicInterface {
    @Override
    public String returnDefault() {
        return "Devolvemos por React";
    }
}
