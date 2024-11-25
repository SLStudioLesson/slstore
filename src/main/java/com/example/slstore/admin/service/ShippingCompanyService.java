package com.example.slstore.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.slstore.admin.dto.ShippingCompanyDto;
import com.example.slstore.admin.dto.ShippingCompanyListDto;
import com.example.slstore.common.entity.ShippingCompany;
import com.example.slstore.common.repository.ShippingCompanyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShippingCompanyService {

    @Autowired
    private ShippingCompanyRepository shippingCompanyRepository;

    /**
     * 配送業者一覧を取得
     */
    public List<ShippingCompanyListDto> getAllShippingCompanies() {
        return shippingCompanyRepository.findAll().stream()
            .map(entity -> entity.toDto().toListDto())
            .collect(Collectors.toList());
    }

    /**
     * アクティブな配送業者のみ取得
     */
    public List<ShippingCompanyListDto> getActiveShippingCompanies() {
        return shippingCompanyRepository.findByIsActiveTrue().stream()
            .map(entity -> entity.toDto().toListDto())
            .collect(Collectors.toList());
    }

    /**
     * 配送業者の詳細を取得
     * @throws NotFoundException 
     */
    public ShippingCompanyDto getShippingCompanyById(Long id) throws NotFoundException {
        return shippingCompanyRepository.findById(id)
            .map(ShippingCompany::toDto)
            .orElseThrow(() -> new NotFoundException());
    }

    /**
     * 配送業者を新規登録
     * @throws Exception 
     */
    public ShippingCompanyDto createShippingCompany(ShippingCompanyDto dto) throws Exception {
        // 配送業者名の重複チェック
        if (shippingCompanyRepository.findByName(dto.getName()).isPresent()) {
            throw new Exception("この配送業者名は既に登録されています。");
        }

        // 最大配送日数のバリデーション
        validateDeliveryDays(dto);

        ShippingCompany entity = dto.toEntity();
        entity = shippingCompanyRepository.save(entity);
        return entity.toDto();
    }

    /**
     * 配送業者情報を更新
     * @throws Exception 
     */
    public ShippingCompanyDto updateShippingCompany(Long id, ShippingCompanyDto dto) throws Exception {
        ShippingCompany existingCompany = shippingCompanyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException());

        // 配送業者名の重複チェック（自分以外）
        if (shippingCompanyRepository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new Exception("この配送業者名は既に登録されています。");
        }

        // 最大配送日数のバリデーション
        validateDeliveryDays(dto);

        // 更新処理
        existingCompany.updateFromDto(dto);
        existingCompany = shippingCompanyRepository.save(existingCompany);
        return existingCompany.toDto();
    }

    /**
     * 配送業者の論理削除
     * @throws NotFoundException 
     */
    public void deleteShippingCompany(Long id) throws NotFoundException {
        ShippingCompany company = shippingCompanyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException());
        
        company.setActive(false);
        shippingCompanyRepository.save(company);
    }

    /**
     * 配送日数のバリデーション
     * @throws Exception 
     */
    private void validateDeliveryDays(ShippingCompanyDto dto) throws Exception {
        if (dto.getDeliveryDaysMax() < dto.getDeliveryDaysMin()) {
            throw new Exception("最大配送日数は最小配送日数より大きい値を設定してください。");
        }
    }
}
