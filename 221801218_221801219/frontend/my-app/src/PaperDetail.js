import React from 'react';
import { withRouter } from 'react-router-dom';
import 'antd/dist/antd.css';
import './index.css';
import { Typography, Divider } from 'antd';
import { Layout } from 'antd';

const { Title, Paragraph, Text } = Typography;
const { Content } = Layout;
const axios = require('axios');

class CreateSearchDetailPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id
            , isLoad: false
            , paperData: {}
        }
    }
    componentDidMount() {
        const _this = this
        let url = "http://localhost:3000/api/v1/paper/view/" + this.state.id
        axios.get(url)
            .then(function (response) {
                if (response.data.code == 0) {
                    _this.setState({
                        paperData: response.data.data,
                        isLoad: true
                    })
                }
                _this.setState({
                    isLoad: true
                })
            }).catch(function (error) {
                console.log(error);
            }).finally(function () {
            });
    }
    render() {
        return (
            !this.state.isLoad ? "loading" : (
                <div>
                    <Content
                        className="site-layout-background"
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            minHeight: 280,
                        }}
                    >
                        <Typography>
                            <Title>{this.state.paperData.title}</Title>
                            <Paragraph>Meeting:
                    {this.state.paperData.meeting}&nbsp;&nbsp;&nbsp;&nbsp;Year:
                    {this.state.paperData.year}
                            </Paragraph>
                            <a href={this.state.paperData.origin_link}>
                                Origin Link:{this.state.paperData.origin_link}
                            </a>
                            <Title level={2}>Abstract</Title>
                            <Paragraph>
                                {this.state.paperData.abstract}
                            </Paragraph>

                            <Paragraph>
                                <Text strong>
                                    Keywords:
                        </Text>{JSON.stringify(this.state.paperData.keywords)}
                            </Paragraph>
                            <Divider />
                        </Typography>
                    </Content>
                </div>
            )
        )
    }
}

export default withRouter(CreateSearchDetailPage);