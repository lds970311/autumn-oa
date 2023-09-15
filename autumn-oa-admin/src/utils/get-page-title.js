import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Autumn办公系统后台'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
