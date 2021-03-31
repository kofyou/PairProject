import React from 'react';
import { withRouter } from 'react-router-dom';
import 'antd/dist/antd.css';
import CollectionsPage from './LoginForm';
import CreateSearchPage from './SearchDetail';
import './index.css';
import CreateListPage from './MyPaperList';
import CreateSearchPageList from './SearchPaper';
import CreateSearchDetailPage from './PaperDetail';
import StatisticPage from './DataStatistics';
import {
  Space,
  Select,
  Form,
  Drawer,
  Layout,
  Menu,
  Input,
  Button
} from 'antd';
import {
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  StarOutlined,
  BarChartOutlined,
  SearchOutlined,
} from '@ant-design/icons';

var a, b, c
const { Search } = Input;
const { Header, Sider } = Layout;

const ChoosePage = ({ id }) => {
  switch (id) {
    case "SearchPaperDetail":
      return (<CreateSearchDetailPage />);
    case "MyPaperList":
      return (<CreateListPage />);
    case "SearchPaperList":
      return (<CreateSearchPageList />);
    case "StatisticPage":
      return (<StatisticPage />);
    default:
      return (<CreateSearchPage />);
  }
}

class Home extends React.Component {
  onSearchP = () => {
    let { history } = this.props
    console.log('!!!' + a + b + c)
    history.push({
      pathname: '/SearchPaperList',
      state: {
        paperNameSearch: a,
        paperMeetingSearch: b,
        paperWordsSearch: c
      }
    })
  }

  onSearch = (paperNameSearch) => {
    let { history } = this.props
    history.push({
      pathname: '/SearchPaperList',
      state: {
        paperNameSearch: paperNameSearch,
        paperMeetingSearch: null,
        paperWordsSearch: null
      }
    })
  };

  showSearchDrawer = () => {
    this.setState({
      searchDrawervisible: true,
    });
  };

  returnHome = () => {
    let { history } = this.props
    history.push({ pathname: '/' })
  };

  onClose = () => {
    this.setState({
      visible: false,
      searchDrawervisible: false,
    });
  };

  changePageToPaperList = () => {
    let { history } = this.props
    history.push({ pathname: '/MyPaperList' })
  }

  changePageToDataStatistic = () => {
    let { history } = this.props
    history.push({ pathname: '/StatisticPage' })
  }

  state = {
    collapsed: true,
    searchDrawervisible: false,
    placement: 'left',
  };

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };

  changeData = (e) => {
    console.log(e.target.value)
    a = e.target.value
  }

  onChange(value) {
    console.log(value);
    b = value
  }

  changeData3 = (e) => {
    console.log(e.target.value)
    c = e.target.value
  }

  render() {
    return (
      <div>
        <Drawer
          title="高级搜索"
          placement={this.state.placement}
          closable={false}
          onClose={this.onClose}
          visible={this.state.searchDrawervisible}
          key={this.state.placement}
        >
          <Form
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 14,
            }}
            layout="horizontal"
            onFinish={this.onSearchP}
          >
            <Form.Item label="标题" name="title" rules={[{ required: true, message: 'Please input paper title!' }]}>
              <Input onChange={(e) => this.changeData(e)} />
            </Form.Item>
            <Form.Item label="顶会">
              <Select onChange={this.onChange}>
                <Select.Option value="CVPR">CVPR</Select.Option>
                <Select.Option value="ICCV">ICCV</Select.Option>
                <Select.Option value="ECCV">ECCV</Select.Option>
              </Select>
            </Form.Item>
            <Form.Item label="关键词" >
              <Input onChange={(e) => this.changeData3(e)} />
            </Form.Item>
            <Form.Item >
              <Button id= "submit-button" type="primary" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Form>
        </Drawer>

        <Layout>
          <Sider trigger={null}
            collapsible
            collapsed={this.state.collapsed}
            theme="light"
          >
            <div className="logo">
              <img id="logo-img" src="./img/46.png" alt="" onClick={this.returnHome} />
            </div>
            <Menu theme="light" mode="inline" defaultSelectedKeys={['']}>
              <Menu.Item key="1" icon={<StarOutlined />} onClick={this.changePageToPaperList.bind(this)}>
                我的论文列表
            </Menu.Item>
              <Menu.Item key="2" icon={<BarChartOutlined />} theme="light" onClick={this.changePageToDataStatistic.bind(this)}>
                论文分析
            </Menu.Item>
            
              <Menu.Item key="3" icon={<SearchOutlined />} theme="light" onClick={this.showSearchDrawer}>
                高级搜索
            </Menu.Item>
            </Menu>
          </Sider>

          <Layout className="site-layout">
            <Header className="site-layout-background" style={{ padding: 0 }}>
              {React.createElement(this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
                className: 'trigger',
                onClick: this.toggle,
              })}

              <Space id="search-button" >
                <Search placeholder="input search text" style={{ width: 420 }} onSearch={(this.onSearch.bind(this))} enterButton />
              </Space>

              <CollectionsPage />
            </Header>
            <ChoosePage id={this.props.match.params.id} />
          </Layout>
        </Layout>
      </div>
    );
  }
}

export default withRouter(Home);