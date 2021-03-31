import React from 'react';
import { Link, withRouter } from 'react-router-dom';
import 'antd/dist/antd.css';
import './SearchPaper.css';
import './index.css';
import { Button, Pagination, List, Avatar, Layout } from 'antd';
import { PlusOutlined } from '@ant-design/icons';

var save
const axios = require('axios');
var paperDataJson = {}
const { Content } = Layout;

const subscribePaper = (paperId) => {
  axios.defaults.withCredentials = true
  let url = 'http://pingleme.top:3000/api/v1/paper/subscribe'
  axios.post(url, {
    withCredentials: true,
    paper_id: paperId,
  })
    .then(function (response) {
      if (response.data.code == 0) {
        alert("添加成功！")
      }
      if (response.data.msg == "论文请勿重复订阅") {
        alert("该论文已在你的论文列表中，请勿重复订阅！")
      }
    }).catch(function (error) {
      console.log(error);
    }).finally(function () {
    });
};

class CreateSearchPageList extends React.Component {
  constructor(props) {
    super(props);
    save = this.props.location.state.paperNameSearch
    this.state = { pageNum: 0, searchData: this.props.location.state.paperNameSearch, isLoad: false };
  }

  componentDidMount() {
    const _this = this
    let urlAdd = _this.state.pageNum == 0 ? '' : '&page=' + _this.state.pageNum
    let urlAdd2 = !_this.props.location.state.paperMeetingSearch ? '' : '&meeting=' + _this.props.location.state.paperMeetingSearch
    let urlAdd3 = !_this.props.location.state.paperWordsSearch ? '' : '&keyword=' + _this.props.location.state.paperWordsSearch
    let url = 'http://pingleme.top:3000/api/v1/search?title=' + _this.props.location.state.paperNameSearch + urlAdd + urlAdd2 + urlAdd3

    axios.get(url)
      .then(function (response) {
        if (response.data.code == 0) {
          paperDataJson.paperData = response.data.data.papers
          paperDataJson.paperCount = response.data.data.page_count
          paperDataJson.pageAt = response.data.data.page
          paperDataJson.total = response.data.data.total
          _this.setState({
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

  componentDidUpdate() {
    if (!this.state.isLoad || save != this.props.location.state.paperNameSearch) {
      const _this = this
      let urlAdd
      if (save != _this.props.location.state.paperNameSearch) {
        urlAdd = '&page=1'
      } else {
        urlAdd = this.state.pageNum == 0 ? '' : '&page=' + this.state.pageNum
      }

      let url = 'http://pingleme.top:3000/api/v1/search?title=' + this.props.location.state.paperNameSearch + urlAdd

      axios.get(url)
        .then(function (response) {
          if (response.data.code == 0) {
            paperDataJson.paperData = response.data.data.papers
            paperDataJson.paperCount = response.data.data.page_count
            paperDataJson.pageAt = response.data.data.page
            paperDataJson.total = response.data.data.total
            if (!_this.state.isLoad) {
              _this.setState({
                isLoad: true
              })
            }
            if (save != _this.props.location.state.paperNameSearch) {
              save = _this.props.location.state.paperNameSearch
              _this.setState({
                pageNum: 1
              })
            }
          }
        }).catch(function (error) {
          console.log(error);
        }).finally(function () {
        });
    }
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
            <div>
              <p id="result-infor">从论文列表获得 {paperDataJson.total} 条结果，以下是第 {paperDataJson.pageAt} 页</p>
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
                    <Button icon={<PlusOutlined />} onClick={() => { subscribePaper(item.id) }}>添加论文</Button>
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
    );
  }
};

export default withRouter(CreateSearchPageList);