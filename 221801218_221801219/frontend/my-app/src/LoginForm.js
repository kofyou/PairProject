import React, { useState } from 'react';
import { Select, Popconfirm, Checkbox, Button, Modal, Form, Input } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import './index.css';
import { withRouter } from 'react-router-dom';
import './LoginForm.css';
import cookie from 'react-cookies';

const { Option } = Select;
const axios = require('axios');
const children = [];
for (let i = 1; i < 10; i++) {
    children.push(<Option key={i}>{i}</Option>);
}

const layout = {
    labelCol: {
        span: 8,
    },
    wrapperCol: {
        span: 16,
    },
};

const tailLayout = {
    wrapperCol: {
        offset: 8,
        span: 16,
    },
};

const onFinish = (values) => {

};

const onRegisterFinish = (values) => {
    const axios = require('axios');
    axios.defaults.withCredentials = true
    let tagData = cookie.load("registerValue")
    const elements = [];
    tagData.forEach((item) => {
        elements.push(
            { id: parseInt(item) }
        )
    });
    let result = {
        user_id: values.username
        , password: values.password
        , password_confirm: values.password
    }
    console.log(result)
    axios.defaults.withCredentials = true
    axios.post("http://pingleme.top:3000/api/v1/signup", result)
        .then(function (response) {
        })
        .catch(function (error) {
            console.log(error);
        })
        .finally(function () {
        });
        alert("注册成功！")
};

const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};


class CreateRegisterForm extends React.Component {

    render() {

        return (
            <Form
                {...layout}
                name="basic"
                onFinish={onRegisterFinish}
                onFinishFailed={onFinishFailed}
            >
                <Form.Item
                    label="Username"
                    name="username"

                    rules={[
                        {
                            required: true,
                            message: 'Please input your username!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                    ]}
                >
                    <Input.Password />
                </Form.Item>
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                        Submit
                </Button>
                </Form.Item>
            </Form>
        )

    }
}

const CreateLoginForm = () => {

    const [visible, setVisible] = useState(false);

    const onCreate = (values) => {
        console.log('Received values of form: ', values);
        setVisible(false);
    };
    return (
        <Form
            name="normal_login"
            className="login-form"
            initialValues={{ remember: true }}

            onFinish={(values) => {
                const axios = require('axios');
                axios.defaults.withCredentials = true
                axios.post("http://pingleme.top:3000/api/v1/login", {
                    user_id: values.user_id,
                    password: values.password
                }).then(response => {
                    if (response.data.code == 0) {
                        cookie.save("loginState", "true", { path: "/" })
                        cookie.save("account", values.user_id, { path: "/" });
                    }
                    alert("登录成功！")
                    window.location.reload()
                }).catch(function (error) {
                    console.log(error);
                }).finally(function () {
                });
                

            }}
        >
            <Form.Item
                name="user_id"
                rules={[{ required: true, message: 'Please input your Username!' }]}
            >
                <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[{ required: true, message: 'Please input your Password!' }]}
            >
                <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    Log in
                </Button>
                Or
                <Button type="link"
                    onClick={() => {
                        setVisible(true);
                    }}>
                    现在注册！
                </Button>
                <CollectionCreateRegisterForm
                    visible={visible}
                    onCreate={onCreate}
                    onCancel={() => {
                        setVisible(false);
                    }}
                />
            </Form.Item>
        </Form>
    )
};

const CollectionCreateLoginForm = ({ visible, onCreate, onCancel }) => {
    const [form] = Form.useForm();
    return (
        <Modal
            visible={visible}
            title="登录界面"
            okText="Create"
            cancelText="Cancel"
            onCancel={onCancel}
            onOk={() => {
                form
                    .validateFields()
                    .then((values) => {
                        form.resetFields();
                        onCreate(values);
                    })
                    .catch((info) => {
                        console.log('Validate Failed:', info);
                    });
            }}
        >
            <CreateLoginForm />
        </Modal>
    );
};


const CollectionCreateRegisterForm = ({ visible, onCreate, onCancel }) => {
    const [form] = Form.useForm();
    return (
        <Modal
            visible={visible}
            title="Create a new collection"
            okText="Create"
            cancelText="Cancel"
            onCancel={onCancel}
            onOk={() => {
                form
                    .validateFields()
                    .then((values) => {
                        form.resetFields();
                        onCreate(values);
                    })
                    .catch((info) => {
                        console.log('Validate Failed:', info);
                    });
            }}
        >
            <CreateRegisterForm />
        </Modal>
    );
};

const LoginButton = () => {

    const [visible, setVisible] = useState(false);
    const onCreate = (values) => {
        console.log('Received values of form: ', values);
        setVisible(false);
    };
    return (
        <div id="login-button">
            <Button
                key="1"
                type="text"
                onClick={() => {
                    setVisible(true);
                }}
            >
                <p id="login-button-text">
                    登录
                </p>
            </Button>
            <CollectionCreateLoginForm
                visible={visible}
                onCreate={onCreate}
                onCancel={() => {
                    setVisible(false);
                }}
            />

        </div>
    );
}

const QuitAccount = () => {
    cookie.save("loginState", "false", { path: "/" })
    let url = 'http://pingleme.top:3000/api/v1/logout'
    axios.defaults.withCredentials = true
    axios.delete(url, { withCredentials: true, })
        .then(function (response) {

        }).catch(function (error) {
            console.log(error);
        }).finally(function () {
        });

}

const CollectionsPage = () => {
    if (cookie.load("loginState") == "true") {
        return (<div id="account-button">
            <Popconfirm title="确定退出账号吗？" okText="Yes" cancelText="No" onConfirm={() => {
                QuitAccount();
                window.location.reload()
            }
            }>
                <Button type='text'

                >{cookie.load("account")}</Button>
            </Popconfirm>
        </div>);
    }
    else {
        return (<LoginButton />);
    }
}

export default withRouter(CollectionsPage);
