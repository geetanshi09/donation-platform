package com.donation.donation_platform.service;

import com.donation.donation_platform.entity.Campaign;
import com.donation.donation_platform.entity.Donation;
import com.donation.donation_platform.repository.CampaignRepository;
import com.donation.donation_platform.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final CampaignRepository campaignRepository;

    public DonationService(DonationRepository donationRepository, CampaignRepository campaignRepository) {
        this.donationRepository = donationRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public List<Donation> getDonationsByDonorName(String donorName) {
        return donationRepository.findByDonorName(donorName);
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found with id " + id));
    }

    @Transactional
    public Donation makeDonation(Donation donation) {
        // update campaign collected amount
        Campaign campaign = donation.getCampaign();
        campaign.setCollectedAmount(campaign.getCollectedAmount() + donation.getAmount());
        campaignRepository.save(campaign);

        return donationRepository.save(donation);
    }
}
