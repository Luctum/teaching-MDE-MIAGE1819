/**
 * generated by Xtext 2.15.0
 */
package org.xtext.example.mydsl.videoGen;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Video Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getVideoid <em>Videoid</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getLocation <em>Location</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getDescription <em>Description</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.videoGen.VideoDescription#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription()
 * @model
 * @generated
 */
public interface VideoDescription extends EObject
{
  /**
   * Returns the value of the '<em><b>Videoid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Videoid</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Videoid</em>' attribute.
   * @see #setVideoid(String)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Videoid()
   * @model
   * @generated
   */
  String getVideoid();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getVideoid <em>Videoid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Videoid</em>' attribute.
   * @see #getVideoid()
   * @generated
   */
  void setVideoid(String value);

  /**
   * Returns the value of the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Location</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' attribute.
   * @see #setLocation(String)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Location()
   * @model
   * @generated
   */
  String getLocation();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getLocation <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' attribute.
   * @see #getLocation()
   * @generated
   */
  void setLocation(String value);

  /**
   * Returns the value of the '<em><b>Duration</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Duration</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Duration</em>' attribute.
   * @see #setDuration(int)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Duration()
   * @model
   * @generated
   */
  int getDuration();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getDuration <em>Duration</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Duration</em>' attribute.
   * @see #getDuration()
   * @generated
   */
  void setDuration(int value);

  /**
   * Returns the value of the '<em><b>Probability</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Probability</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Probability</em>' attribute.
   * @see #setProbability(int)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Probability()
   * @model
   * @generated
   */
  int getProbability();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getProbability <em>Probability</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Probability</em>' attribute.
   * @see #getProbability()
   * @generated
   */
  void setProbability(int value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Filter</em>' containment reference.
   * @see #setFilter(Filter)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Filter()
   * @model containment="true"
   * @generated
   */
  Filter getFilter();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getFilter <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' containment reference.
   * @see #getFilter()
   * @generated
   */
  void setFilter(Filter value);

  /**
   * Returns the value of the '<em><b>Text</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Text</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Text</em>' containment reference.
   * @see #setText(Text)
   * @see org.xtext.example.mydsl.videoGen.VideoGenPackage#getVideoDescription_Text()
   * @model containment="true"
   * @generated
   */
  Text getText();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.videoGen.VideoDescription#getText <em>Text</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Text</em>' containment reference.
   * @see #getText()
   * @generated
   */
  void setText(Text value);

} // VideoDescription
