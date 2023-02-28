import React, {useEffect, useState} from 'react';
import {BrowserRouter} from "react-router-dom";
import Delete from "./app/static/trash.png";
import Edit from "./app/static/edit-40.png";
import {Button, Col, Container, Pagination, Row, Table} from "react-bootstrap";
import {fetchClients, getClient, removeClient} from "./app/http/clientsApi";
import {observer} from "mobx-react-lite";
import CreateClient from "./app/components/modal/CreateClient";
import "./app/components/style/index.css"

const App = observer(() => {
    const [createClientVisible, setCreateClientVisible] = useState(false);
    const [clients, setClients] = useState([]);
    const [reload, setReload] = useState([]);
    const [client, setClient] = useState([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const table = ['Прізвище', 'Ім\'я', 'По-батькові', '', '']
    const pages = [];
    for (let i = 0; i < totalPages; i++) {
        pages.push(i + 1);
    }

    const fillSetting = () => {
        try {
            fetchClients(page-1).then((data) => {
                setClients(data.content)
                setTotalPages(data.totalPages);
            });
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    useEffect(() => {
        fillSetting()
    }, [page, reload])

    useEffect(() => {
        if (createClientVisible === false) {
            setClient([])
        }
        setReload([])
    }, [createClientVisible])

    const deleteClient = (id) => {
        try {
            removeClient(id).then((data) => {
                alert(data.message);
                setReload([...reload]);
            });
        } catch (e) {
            alert(e.response.data.message)
        }
    };

    const formEdit = (id) => {
        try {
            getClient(id).then((data) => {
                setClient(data)
            });
        } catch (e) {
            alert(e.response.data.message)
        }
        setCreateClientVisible(true)
    };

    return (
        <BrowserRouter>
            <Container>
                <Row>
                    <Col className={"mt-5"}>
                        <Button onClick={() => setCreateClientVisible(true)} className="m-3">
                            Додати анкету</Button>
                    </Col>
                    <Col md={9}>
                        <Table className="m-3" striped bordered hover>
                            <thead>
                            <tr>
                                {table.map((title, index) => <th key={index}>{title}</th>)}
                            </tr>
                            </thead>
                            <tbody>
                            {clients.map(client =>
                                <tr key={client.id}>
                                    <td>
                                        {client.lastname}
                                    </td>
                                    <td>
                                        {client.firstname}
                                    </td>
                                    <td>
                                        {client.middlename}
                                    </td>
                                    <td width="15%">
                                        <Button title="Edit client"
                                                onClick={() => formEdit(client.id)}
                                                className="w-100 btn-success">
                                            <img style={{width: 20, height: 20}} src={Edit} alt="edit"/>
                                        </Button>
                                    </td>
                                    <td className="d-flex justify-content-center">
                                        <Button onClick={() => deleteClient(client.id)}
                                                title="Delete client"
                                                className="w-100 btn  btn-danger">
                                            <img style={{width: 20, height: 20}} src={Delete} alt="delete"/>
                                        </Button>
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                        <Pagination className="">
                            {pages.map(pagePagin =>
                                <Pagination.Item
                                    key={pagePagin}
                                    active={page === pagePagin}
                                    onClick={()=> setPage(pagePagin)}>
                                    {pagePagin}
                                </Pagination.Item>
                            )}
                        </Pagination>
                    </Col>
                </Row>
                <CreateClient client={client} show={createClientVisible} onHide={() => setCreateClientVisible(false)}/>
            </Container>
        </BrowserRouter>
    );
});

export default App;
