@EXECUTION-TAG
Feature: Sample Test Executions
  #@PRECOND_TAG
  Background: Pre-conditions
    Given mitra signing in as "PERSEORANGAN"

  @TEST-CASE-TAG
  Scenario: Mitra open home page
    When mitra see mitra home page
#    When mitra scroll down to "Aktivitas Menguntungkan"
    When mitra tap on MauDikode


#    Then mitra logout