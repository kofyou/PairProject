import React, { useState } from 'react';
import { Link, withRouter } from 'react-router-dom';
import 'antd/dist/antd.css';
import './SearchPaper.css';
import './index.css';
import { Button, Tooltip, Pagination, Statistic, List, Avatar, Layout } from 'antd';
import { MinusOutlined } from '@ant-design/icons';
import './MyPaperList.css';

const axios = require('axios');

var paperDataJson = {}

const { Content } = Layout;

const cancelSubscribePaper = (paperId) => {
  axios.defaults.withCredentials = true
  let url = 'http://localhost:3000/api/v1/paper/unsubscribe'
  axios.post(url, {
    paper_id: paperId,
  })
    .then(function (response) {
      if (response.data.code == 0) {
        alert("取消订阅成功！")
        window.location.reload()
      }
    }).catch(function (error) {
      console.log(error);
    }).finally(function () {
    });
};

class CreateListPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = { pageNum: 0, isLoad: false };
  }

  componentDidMount() {
    axios.defaults.withCredentials = true
    const _this = this
    let urlAdd = _this.state.pageNum == 0 ? '' : '&page=' + _this.state.pageNum
    let url = 'http://localhost:3000/api/v1/user/list' + urlAdd
    axios.get(url)
      .then(function (response) {
        if (response.data.code == 0) {
          paperDataJson.paperData = response.data.data.papers
          paperDataJson.paperCount = response.data.data.page_count
          paperDataJson.pageAt = response.data.data.page
          // window.location.reload()
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
      <div>
        <Content
          className="site-layout-background"
          style={{
            margin: '24px 16px',
            padding: 24,
            minHeight: 280,
          }}

        >
          <div>
            <Button id="search-paper-link" type="link" href="/" >点击获取更多论文</Button>
            <p id="account-list">221801219 的论文列表 (7)</p>
          </div>
          <hr />
          <div id="paparList">
            <List

              itemLayout="horizontal"
              dataSource={paperDataJson.paperData}
              renderItem={item => (
                <List.Item>
                  <p>{item.id}.&nbsp;&nbsp;</p>
                  <List.Item.Meta
                    avatar={<Avatar src="./img/45.jpg" />}
                    title={<Link to={"/SearchPaperDetail/"+item.id} style={{ color: 'black' }}>
                      {item.title}
                    </Link>}
                    description={'会议:' + item.meeting + '发表年份:' + item.year}

                  />
                  {/* <a herf=""></a> */}
                  <Button icon={<MinusOutlined />} onClick={() => { cancelSubscribePaper(item.id) }} >取消订阅</Button>

                  {/* <Statistic title="View" value={112} /> */}
                </List.Item>
              )}
            />
          </div>
          <hr />
          <div id="divide-page">
          <Pagination size="small" pageSizeOptions={[10]} total={paperDataJson.paperCount}
                current={this.state.pageNum} showQuickJumper onChange={(page, pageSize) => {
                  this.setState({
                    pageNum: page,
                    isLoad: false
                  });
                }} />
          </div>
        </Content>
      </div>
    )
  }
}

export default withRouter(CreateListPage);

