package com.example.ScopistoTechnical.service.impl;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.model.Pet;
import com.example.ScopistoTechnical.repository.OwnerRepository;
import com.example.ScopistoTechnical.repository.PetRepository;
import com.example.ScopistoTechnical.service.OwnerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    @Override
    public Optional<AppUser> getOwner(Long id) throws Exception {
        try{
            return ownerRepository.findById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<AppUser> getAllOwners() throws Exception {
        try{
            return Optional.of(ownerRepository.findAll()).orElse(new ArrayList<>());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<AppUser> saveAll(List<AppUser> owners) throws Exception {
        try{
            return ownerRepository.saveAll(owners);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<AppUser> save(AppUser appUser) throws Exception {
        try{
            return Optional.of(ownerRepository.save(appUser));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<AppUser> buyPets() throws Exception {
        try{
            List<Pet> pets = petRepository.findAll();
            pets.sort(Comparator.comparing(Pet::getPrice));

            List<AppUser> owners = ownerRepository.findAll();
            owners.sort(Comparator.comparing(AppUser::getBudget).reversed());

            Queue<AppUser> queueOfOwners = new LinkedList<>(owners);

            for (Pet pet : pets) {
                if (pet.getOwner() != null)
                    continue;
                int buyerCounter = 0;
                while(buyerCounter < queueOfOwners.size()) {
                    buyerCounter++;
                    AppUser owner = queueOfOwners.poll();
                    assert owner != null;
                    if (owner.getBudget().isGreaterThanOrEqual(pet.getPrice())){
                        owner.addPet(pet);
                        owner.getBudget().subtract(pet.getPrice());
                        queueOfOwners.add(owner);
                        pet.makeSound();
                        break;
                    }
                    queueOfOwners.add(owner);
                }
            }
            return ownerRepository.saveAll(queueOfOwners);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
