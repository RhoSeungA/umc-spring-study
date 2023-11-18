package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements  StoreQueyrService{

    StoreRepository storeRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }
}
