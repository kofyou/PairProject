import React from 'react';
import { withRouter } from 'react-router-dom';
import 'antd/dist/antd.css';
import './index.css';
import { Layout, Input, Space } from 'antd';

const { Search } = Input;

const { Content } = Layout;

class CreateSearchPage extends React.Component {

    onSearch = value => {
        console.log(value);
        let { history } = this.props
        history.push({
            pathname: '/SearchPaperList',
            state: {
                paperNameSearch: value,
                paperMeetingSearch: null,
                paperWordsSearch: null
            }
        })
    }

    render() {
        return (
            <div>
                <Content
                    className="site-layout-background"
                    style={{
                        margin: '24px 16px',
                        padding: 24,
                        minHeight: 280,
                    }}
                >
                    <img id="homePage-img" src="./img/6.png" alt="" />
                    <Space id="homePage-search-button" >
                        <Search placeholder="input search text" style={{ width: 420 }} onSearch={this.onSearch.bind(this)} enterButton />
                    </Space>
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                </Content>
            </div>
        );
    }
};

export default withRouter(CreateSearchPage);