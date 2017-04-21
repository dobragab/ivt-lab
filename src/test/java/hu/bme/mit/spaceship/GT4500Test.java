package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimary;
  private TorpedoStore mockSecondary;

  @Before
  public void init(){
    mockPrimary = mock(TorpedoStore.class);
    mockSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_Fail1(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Single_Fail2(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.isEmpty()).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_FailBoth(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Single_FirstPrimaryTwice(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Assert
    assertEquals(true, ship.fireTorpedos(FiringMode.SINGLE));
    when(mockSecondary.isEmpty()).thenReturn(true);
    assertEquals(true, ship.fireTorpedos(FiringMode.SINGLE));
  }

  @Test
  public void fireTorpedos_Single_SecondTwice(){
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Assert
    assertEquals(true, ship.fireTorpedos(FiringMode.SINGLE));
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Fail1(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_All_Fail2(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_All_FailBoth(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }




}
