package vermeg.com.applicationbancaire.Services;

import vermeg.com.applicationbancaire.Models.CGUModel;

public interface CGUService {
        CGUModel Create(CGUModel N);
        CGUModel  Update (CGUModel N);
        CGUModel Getone (Long Id);
        void Delate(Long Id);
}
