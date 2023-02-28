import React, {useEffect, useState} from 'react';
import {Button, Dropdown, Form, Modal} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import {fetchFemaleOccupations, fetchMaleOccupations} from "../../http/occupationApi";
import {createClient, editClient} from "../../http/clientsApi";

const CreateClient = observer(({show, onHide, client}) => {
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [middlename, setMiddlename] = useState('');
    const [gender, setGender] = useState('');
    const [occupation, setOccupation] = useState('');
    const [yearOfBirth, setYearOfBirth] = useState(0);
    const [occupations, setOccupations] = useState([]);
    const [validated, setValidated] = useState(false);
    const [mode, setMode] = useState('');

    useEffect(()=> {
        if (client.length !== 0) {
            setMode('update');
            setVisibleBtn(1, 'visible');
            loadOccupations(client.gender);
            setFirstname(client.firstname);
            setMiddlename(client.middlename);
            setLastname(client.lastname);
            setGender(client.gender);
            setYearOfBirth(client.yearOfBirth);
            setOccupation(client.occupation);
        } else {
            setMode('save');
            setVisibleBtn(0.4, 'none');
            setOccupation("");
        }
        setValidated(false)
    }, [client])

    const setVisibleBtn = (opacity, pointer) => {
        if (document.querySelector('#blocked-div')) {
            let visibleDiv = document.querySelector('#blocked-div');
            visibleDiv.style.opacity = opacity;
            visibleDiv.style.pointerEvents = pointer;
        }
    }

    const setGenderType = (genderType) => {
        setOccupation("")
        setGender(genderType)
        setVisibleBtn(1, 'visible')
        loadOccupations(genderType)
    }

    const loadOccupations = (gender) => {
        try {
            if (gender === "MALE") {
                document.querySelector('#MALE').setAttribute('checked', '');
                fetchMaleOccupations().then(data => {
                    setOccupations(data)
                })
            }
            if (gender === "FEMALE") {
                document.querySelector('#FEMALE').setAttribute('checked', '');
                fetchFemaleOccupations().then(data => {
                    setOccupations(data)
                })
            }
        } catch (e) {
            alert(e.response.data.message)
        }
    };

    const saveClient = (mode) => {
        if (isValid()) {
            try {
                if (mode === 'save') {
                    createClient(setClientData()).then(() => {
                        alert('Анкету успішно додано')
                        onHide();

                    })
                }
                if (mode === 'update') {
                    editClient(client.id, setClientData()).then(() => {
                        alert('Анкету успішно оновлено')
                        onHide();
                    })
                }

            } catch (e) {
                alert(e.response.data.message)
            }
        }
    }

    function isValid() {
        setValidated(true);
        const form = document.querySelector('#formData');
        if (firstname.trim().length < 1) {
            form.querySelector('#firstnameControl').value = ''
        }
        if (middlename.trim().length < 1) {
            form.querySelector('#middlenameControl').value = ''
        }
        if (lastname.trim().length < 1) {
            form.querySelector('#lastnameControl').value = ''
        }
        return form.checkValidity() !== false;
    }

    function setClientData(){
        return {
            'firstname': firstname.trim(),
            'middlename': middlename.trim(),
            'lastname': lastname.trim(),
            'yearOfBirth': yearOfBirth,
            'gender': gender,
            'occupation': occupation
        }
    }

    return (
        <div>
            <Modal size="lg" show={show} onHide={onHide}>
                <Modal.Header closeButton>
                    <Modal.Title>Додати анкету</Modal.Title>
                </Modal.Header>
                <Modal.Body className="m-3">
                    <Form id='formData' noValidate validated={validated} >
                        <Form.Group controlId="firstnameControl">
                            <Form.Label>Ім'я</Form.Label>
                            <Form.Control required
                                          className="mb-3"
                                          defaultValue={client.firstname}
                                          type="text"
                                          onChange={e =>setFirstname(e.target.value)}
                            />
                            <Form.Control.Feedback type="invalid">
                                Введіть ім'я
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group controlId="middlenameControl">
                            <Form.Label>По-батькові</Form.Label>
                            <Form.Control required
                                          className="mb-3"
                                          defaultValue={client.middlename}
                                          type="text"
                                          onChange={e => setMiddlename(e.target.value)}
                            />
                            <Form.Control.Feedback type="invalid">
                                Введіть по-батькові
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group controlId="lastnameControl">
                            <Form.Label>Прізвище</Form.Label>
                            <Form.Control required
                                          className="mb-3"
                                          defaultValue={client.lastname}
                                          type="text"
                                          onChange={e => setLastname(e.target.value)}
                            />
                            <Form.Control.Feedback type="invalid">
                                Введіть прізвище
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Рік народження</Form.Label>
                            <Form.Control required
                                          className="mb-3 no-spinner total-amount"
                                          type="number"
                                          defaultValue={client.yearOfBirth}
                                          onChange={e => setYearOfBirth(Number(e.target.value))}
                            />
                            <Form.Control.Feedback type="invalid">
                                Введіть рік народження
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Стать</Form.Label>
                            <Form.Check onChange={(e) => {setGenderType(e.target.value)}}
                                        value="MALE"
                                        type="radio"
                                        label="Чоловік"
                                        name="gender"
                                        id="MALE"
                                        required
                            />
                            <Form.Check onChange={(e) => {setGenderType(e.target.value)}}
                                        value="FEMALE"
                                        type="radio"
                                        label="Жінка"
                                        required
                                        feedback="Виберіть стать"
                                        feedbackType="invalid"
                                        name="gender"
                                        id="FEMALE"
                            />
                        </Form.Group>
                        <Form.Group>
                            <Dropdown required id='blocked-div' className="mt-3 ">
                                <Dropdown.Toggle> {occupation || "Рід занять"}</Dropdown.Toggle>
                                <Dropdown.Menu>
                                    {occupations.map(item =>
                                        <Dropdown.Item
                                            onClick={() => setOccupation(item.name)}
                                            key={item.name}>{item.name}
                                        </Dropdown.Item>
                                    )}
                                </Dropdown.Menu>
                            </Dropdown>
                            <Form.Control required hidden
                                          type="text"
                                          defaultValue={occupation}
                            />
                            <Form.Control.Feedback type="invalid">
                                Виберіть рід діяльності
                            </Form.Control.Feedback>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={onHide}>Закрити</Button>
                    <Button variant="primary" onClick={() =>saveClient(mode)}>Зберегти</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
});

export default CreateClient;