package com.example.PFE1.service;

import com.example.PFE1.model.Tour;

import java.util.List;


public interface ITourService {
    Tour createTour(Tour tour);

    Tour getTourById(Long id);

    List<Tour> getAllTours();

    List<Tour> SearchFilter(Tour tour);

    Tour updateTour(Tour tour);

    void deleteTour(Long id);
}
