package vermeg.com.applicationbancaire.Services;


import vermeg.com.applicationbancaire.Models.PIDModel;

import java.util.List;

public interface PIDService {
    PIDModel Create( PIDModel X);
    PIDModel Update ( PIDModel X);
    PIDModel Getone (Long Id);
    List< PIDModel> Totale();
    void Delate(Long Id);
}
