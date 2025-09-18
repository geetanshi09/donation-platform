package com.donation.donation_platform.service;

import com.donation.donation_platform.entity.Campaign;
import com.donation.donation_platform.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id " + id));
    }

    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
